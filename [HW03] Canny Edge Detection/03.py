from PIL import Image
import numpy as np
import math

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

############ Canny Edge Detection ############