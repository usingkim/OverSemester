from PIL import Image
import numpy as np
import math

# Part 1 Gaussian Filtering

# 1. make 2D filters : odd number of rows and columns
def boxfilter(n):
    # using assert statement
    assert n % 2 == 1, "Dimension must be odd"
    # filter should be a numpy array
    filter = np.full((n, n), 1 / (n**2))
    return filter

for n in [3, 7, 4]:
    print(n, " : ", boxfilter(n))

# 2. 1D Gaussian filter
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

for i in [0.3, 0.5, 1, 2]:
    print(i, ":", gauss1d(i))

# 3. 2D Gaussian filter
def gauss2d(sigma):
    # user np.outer with the 1d array from the function gauss1d(sigma)
    filter = gauss1d(sigma)
    filter = np.outer(filter, filter)
    # normalize the values in the filter : sum to 1
    sum_of_filter = np.sum(filter)
    filter = np.divide(filter, sum_of_filter)
    return filter

for i in [0.5, 1]:
    print(gauss2d(i))

# 4. Apply Filter
# 4-a. zero paddings
def convolve2d(array, filter):
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

# 4-b. Gaussian convolution to a 2D array
def gaussconvolve2d(array, sigma):
    # generating a filter with gauss2d
    filter = gauss2d(sigma)
    # apply it to the array with convolve2d
    padding = convolve2d(array, filter)
    return padding

# 4-c. Apply gaussconvolve2d
# Load Dog Image into Python
dog = Image.open('[HW02] Image Filtering/hw2_image/2b_dog.bmp')
# Convert it to a greyscale
grey_dog = dog.convert('L')
# for calculate : numpy -> array, input variable type : np.float32
grey_dog_array = np.asarray(grey_dog, dtype=np.float32)
# apply filter
grey_dog_array = gaussconvolve2d(grey_dog_array, 3)
# for PIL show
grey_dog = Image.fromarray(grey_dog_array)

# 4-d. Use PIL to show both the original and filtered images.
dog.show()
grey_dog.show()

# Part 2 Hybrid Images

# 1. Low Frequency Image
def gauss(image, sigma):
    # rgb seperately
    image = Image.Image.split(image)
    # for calculation : Image -> array
    image_array = [np.asarray(rgb, dtype=np.float32) for rgb in image]
    # apply filter
    image_array = [gaussconvolve2d(rgb, sigma) for rgb in image_array]
    # for PIL
    image = [Image.fromarray(rgb).convert('L') for rgb in image_array]
    # back to the image to display
    image = Image.merge("RGB", image)
    return image

# 2. High Frequency Image
def sharpen(image, sigma):
    # rgb seperately
    image = Image.Image.split(image)
    # for calculation : Image -> array
    image_array = [np.asarray(rgb, dtype=np.float32) for rgb in image]
    # apply filter
    filtered_image_array = [gaussconvolve2d(rgb, sigma) for rgb in image_array]
    # image = image - low + 128(for visualized)
    result_image_array = [image_array[i] - filtered_image_array[i] for i in range(len(image_array))]
    result_image_array = np.add(result_image_array, 128)
    # for PIL
    image = [Image.fromarray(rgb).convert('L') for rgb in result_image_array]
    # back to the image to display
    image = Image.merge("RGB", image)
    return image

# 3. add low and high frequency images => hybrid image
def hybrid(image1, image2, sigma):
    low = gauss(image1, sigma)
    high = sharpen(image2, sigma)
    # Image to array for calculation
    low_array = np.asarray(low)
    high_array = np.asarray(high)
    # in sharpen function => +128 for visualization. 
    # just visualization => the value is no longer necessary -128
    result = low_array + high_array - 128
    # over 255 -> 255, less than 0 -> 0
    result[result > 255] = 255
    result[result < 0] = 0
    # array -> Image
    result = Image.fromarray(result)
    return result

image1 = Image.open("[HW02] Image Filtering/hw2_image/3a_eiffel.bmp")
image2 = Image.open("[HW02] Image Filtering/hw2_image/3b_tower.bmp")

sigma = 7

low_image1 = gauss(image1, sigma)
low_image2 = gauss(image2, sigma)

low_image1.show()
low_image2.show()
# low_image1.save('[HW02] Image Filtering/hw2_image/low_eiffel.bmp')
# low_image2.save("[HW02] Image Filtering/hw2_image/low_tower.bmp")

high_image1 = sharpen(image1, sigma)
high_image2 = sharpen(image2, sigma)

high_image1.show()
high_image2.show()
# high_image1.save('[HW02] Image Filtering/hw2_image/high_eiffel.bmp')
# high_image2.save("[HW02] Image Filtering/hw2_image/high_tower.bmp")

hybrid_image = hybrid(image1, image2, sigma)
hybrid_image.show()
# hybrid_image.save("[HW02] Image Filtering/hw2_image/hybrid.bmp")
hybrid_image = hybrid(image2, image1, sigma)
hybrid_image.show()
# hybrid_image.save("[HW02] Image Filtering/hw2_image/hybrid.bmp")