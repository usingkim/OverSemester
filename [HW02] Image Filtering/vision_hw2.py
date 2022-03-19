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

# for i in [3, 7, 4]:
#     print(boxfilter(i))

# 2. 1D Gaussian filter
def gauss1d(sigma):
    # length : sigma * 6, and then rounded up to the next odd integer
    length = math.ceil(sigma * 6) // 2 * 2 + 1
    # filter generate
    filter = np.arange(length // 2 * (-1), length // 2 + 1)
    # x is the ditance of an array value from the center
    center = filter[length // 2]
    x = np.abs(np.subtract(filter, center))
    # apply function
    filter = np.exp(x ** 2 * -1 / (2 * sigma ** 2))
    return filter

# for i in [0.3, 0.5, 1, 2]:
#     print(gauss1d(i))

# 3. 2D Gaussian filter
def gauss2d(sigma):
    # user np.outer with the 1d array from the function gauss1d(sigma)
    filter = gauss1d(sigma)
    filter = np.outer(filter, filter)
    # normalize the values in the filter : sum to 1
    sum_of_filter = np.sum(filter)
    filter = np.divide(filter, sum_of_filter)
    return filter

# for i in [0.5, 1]:
#     print(gauss2d(i))

# 4.
# 4-a. zero paddings
def convolve2d(array, filter):
    