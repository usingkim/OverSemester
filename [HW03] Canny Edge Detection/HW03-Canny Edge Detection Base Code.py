from PIL import Image
import math
import numpy as np

"""
Get and use the functions associated with gaussconvolve2d that you used in the last HW02.
"""
def gauss1d(sigma):
    # length : sigma * 6, and then rounded up to the next odd integer
    length = math.ceil(sigma * 6) // 2 * 2 + 1
    # generate 1D array of x values
    x = np.arange(length // 2 * (-1), length // 2 + 1)
    # x is the ditance of an array value from the center, center is 0
    x = np.abs(np.subtract(x, 0))
    # apply function
    filter = np.exp(x ** 2 * -1 / (2 * (sigma ** 2)))
    # normalize the values in the filter : sum to 1
    sum_of_filter = np.sum(filter)
    filter = np.divide(filter, sum_of_filter)
    return filter

def gauss2d(sigma):
    # user np.outer with the 1d array from the function gauss1d(sigma)
    filter = gauss1d(sigma)
    filter = np.outer(filter, filter)
    # normalize the values in the filter : sum to 1
    sum_of_filter = np.sum(filter)
    filter = np.divide(filter, sum_of_filter)
    return filter

def convolve2d(array,filter):
    # f x f kernel, m = (f-1)/2 : m space < fill with zeros
    f = len(filter)
    m = (f-1) // 2
    # add zero padding
    array_padding = np.pad(array, ((m, m), (m, m)), 'constant', constant_values=0)
    # for convolution : up, down, left and right changes
    for_convolution = np.flip(filter)
    # array : numpy array, so no len() => asarray => array
    array_numpy = np.asarray(array)
    # for result : len
    row, col = len(array_numpy), len(array_numpy[0])
    # result => save to 'image' numpy array variable.
    image = np.zeros((row, col), dtype=np.float32)
    # performs convolution to the image with zero paddings
    for i in range(row):
        for j in range(col):
            image[i][j] = np.sum(array_padding[i:i+len(filter), j:j+len(filter)] * for_convolution)
    return image

def gaussconvolve2d(array,sigma):
    # generating a filter with gauss2d
    filter = gauss2d(sigma)
    # apply it to the array with convolve2d
    padding = convolve2d(array, filter)
    return padding

def sobel_filters(img):
    """ Returns gradient magnitude and direction of input img.
    Args:
        img: Grayscale image. Numpy array of shape (H, W).
    Returns:
        G: Magnitude of gradient at each pixel in img.
            Numpy array of shape (H, W).
        theta: Direction of gradient at each pixel in img.
            Numpy array of shape (H, W).
    Hints:
        - Use np.hypot and np.arctan2 to calculate square root and arctan
    """ 
    # apply Sobel filter in the x, y direction
    filter_x = [[-1, 0, 1], [-2, 0, 2], [-1, 0, 1]]
    filter_y = [[1, 2, 1], [0, 0, 0], [-1, -2, -1]]
    # apply the convolve2d function to obtain the intensity x, y value
    Ix = convolve2d(img, filter_x)
    Iy = convolve2d(img, filter_y)
    # Formulate Gradient and Theta
    #   G: Magnitude of gradient at each pixel in img.
    #   theta: Direction of gradient at each pixel in img.
    G = np.hypot(Ix, Iy)
    theta = np.arctan2(Iy, Ix)
    # mapping value
    G_max = np.max(G)
    G_min = np.min(G)
    G = G / (G_max - G_min) * 255
    
    return (G, theta)

def non_max_suppression(G, theta):
    """ Performs non-maximum suppression.
    This function performs non-maximum suppression along the direction
    of gradient (theta) on the gradient magnitude image (G).
    Args:
        G: gradient magnitude image with shape of (H, W).
        theta: direction of gradients with shape of (H, W).
    Returns:
        res: non-maxima suppressed image.
    """
    # theta is radians. radian -> degree
    theta = np.rad2deg(theta)
    # res.shape = G.shape
    # if pixel is less than neighbor, that pixel is 0. => zeros
    res = np.zeros(G.shape)

    for i in range(1, G.shape[0]-1):
        for j in range(1, G.shape[1]-1):

            # angle standard = [0, 45, 90, 135] => 0 <= __ < 180
            angle = theta[i][j]
            if angle >= 180:
                angle -= 180
            if angle < 0:
                angle += 180

            # 중간 지점을 기준으로 angle 값이 가리키는 방향에 있는 픽셀 2개를 기준으로 더 큰 값이 있는지 없는지 비교한다.
            num1, num2 = 0, 0
            if 22.5 <= angle < 67.5: # 45
                num1 = G[i-1][j+1]
                num2 = G[i+1][j-1]
            elif 67.5 <= angle < 112.5: # 90
                num1 = G[i-1][j]
                num2 = G[i+1][j]
            elif 112.5 <= angle < 157.5: # 135
                num1 = G[i-1][j-1]
                num2 = G[i+1][j+1]
            else: # 0 : 157.5 ~ 180 / 0 ~ 22.5
                num1 = G[i][j-1]
                num2 = G[i][j+1]
            
            # num1 보다 크고 num2 보다 큰  경우 G[i][j]를 res[i][j]에 넣어준다.
            if (G[i][j] > num1 and G[i][j] > num2):
                res[i][j] = G[i][j]

    return res

def double_thresholding(img):
    """ 
    Args:
        img: numpy array of shape (H, W) representing NMS edge response.
    Returns:
        res: double_thresholded image.
    """
    # use the expressions to determine threshold values
    diff = np.max(img) - np.min(img)
    T_high = np.min(img) + diff * 0.15
    T_low = np.min(img) + diff * 0.03

    # default = no-relationship
    res = np.zeros(img.shape)
    # strong : 175 + 80 = 255
    res += np.where(img > T_high, 175, 0) 
    # weak
    res += np.where(T_low < img, 80, 0) 
    
    return res

def hysteresis(img):
    """ Find weak edges connected to strong edges and link them.
    Iterate over each pixel in strong_edges and perform depth first
    search across the connected pixels in weak_edges to link them.
    Here we consider a pixel (a, b) is connected to a pixel (c, d)
    if (a, b) is one of the eight neighboring pixels of (c, d).
    Args:
        img: numpy array of shape (H, W) representing NMS edge response.
    Returns:
        res: hysteresised image.
    """

    strong = 255
    weak = 80

    # use dfs on all strong edges to obtain connected weak edges
    def dfs(i, j):
        # neighbor 8 pixels
        for a in range(i-1, i+2):
            for b in range(j-1, j+2):
                # weak인 경우, strong으로 바꿔주고 그 점을 기준으로 또 다시 dfs
                if img[a][b] == weak:
                    img[a][b] = strong
                    dfs(a, b)
                # 종료 조건 설정. strong인 경우 탐색 중지
                elif img[a][b] == strong:
                    continue
    
    # weak edge -> strong [link]
    for i in range(1, img.shape[0] - 1):
        for j in range(1, img.shape[1] - 1):
            if img[i][j] == strong:
                dfs(i, j)

    # strong인 경우만 strong value 그대로 복사, 아니면 0으로
    res = np.where(img == strong, strong, 0)
    return res

def main():

    ######### 1. Noise reduction

    # Load iguana.bmp
    iguana = Image.open('[HW03] Canny Edge Detection/iguana.bmp')
    # Convert it to Greyscale
    grey_iguana = iguana.convert('L')
    # to numpy array
    grey_iguana_array = np.asarray(grey_iguana, dtype=np.float32)
    # blurs the image using gaussconvolved2d(array,sigma)
    new_iguana_array = gaussconvolve2d(grey_iguana_array, 1.6)
    # use PIL to show both the original and filtered images.
    new_iguana = Image.fromarray(new_iguana_array)
    iguana.show()
    new_iguana.show()

    ######### 2. Finding the intensity gradient of the image
    # sobel_filters(img) => args : img -> Grayscale image. Numpy array of shape (H, W).
    G, theta = sobel_filters(new_iguana_array)

    ######### 3. Non-Maximum Suppression
    non_max_img_array = non_max_suppression(G, theta)

    ######### 4. Double threshold
    double_img_array = double_thresholding(non_max_img_array)

    ######### 5. Edge Tracking by hysteresis
    edge_tracking_array = hysteresis(double_img_array).astype(np.float32)

    img = Image.fromarray(edge_tracking_array)
    img.show()

if __name__ == '__main__':
    main()