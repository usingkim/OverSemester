from hashlib import shake_128
from turtle import dot
import numpy as np
import math
import random


def RANSACFilter(
        matched_pairs, keypoints1, keypoints2,
        orient_agreement, scale_agreement):
    """
    This function takes in `matched_pairs`, a list of matches in indices
    and return a subset of the pairs using RANSAC.
    Inputs:
        matched_pairs: a list of tuples [(i, j)],
            indicating keypoints1[i] is matched
            with keypoints2[j]
        keypoints1, 2: keypoints from image 1 and image 2
            stored in np.array with shape (num_pts, 4)
            each row: row, col, scale, orientation
        *_agreement: thresholds for defining inliers, floats
    Output:
        largest_set: the largest consensus set in [(i, j)] format

    HINTS: the "*_agreement" definitions are well-explained
           in the assignment instructions.
    """
    assert isinstance(matched_pairs, list)
    assert isinstance(keypoints1, np.ndarray)
    assert isinstance(keypoints2, np.ndarray)
    assert isinstance(orient_agreement, float)
    assert isinstance(scale_agreement, float)
    ## START
    largest_set = []

    for _ in range(1):
        # random하게 keypoints1과 keypoints2에서 뽑아낸다.
        random_k1 = keypoints1[random.randint(0, keypoints1.shape[0])]
        random_k2 = keypoints2[random.randint(0, keypoints2.shape[0])]

        # 두개의 orientation 값을 기준으로, inlier의 기준이 되는 orientation 값을 설정한다.
        # orientation = 두 값 차이에 threshold를 더하고 빼준 값이다.
        orientation = math.degrees(random_k1[3])- math.degrees(random_k2[3])
        orientation = [orientation-orient_agreement, orientation+orient_agreement]

        # 두개의 scale 값을 기준으로, inlier의 기준이 되는 scale 값을 설정한다.
        # scale = (s2/s1)에 (s2/s1)*scale_agreement 값을 더하고 빼준 값이다.
        scale = random_k2[2] / random_k1[2]
        scale = [scale * (1 - scale_agreement), scale * (1 + scale_agreement)]

        inliers = list()

        print(orientation, scale)

        for i1, k1 in enumerate(keypoints1):
            for i2, k2 in enumerate(keypoints2):
                o = math.degrees(k1[3])- math.degrees(k2[3])
                s = k2[2] / k1[2]

                if orientation[0] < o < orientation[1] and scale[0] < s < scale[1]:
                    #print(math.degrees(random_k1[3]), math.degrees(random_k2[3]), orientation, o)
                    inliers.append([i1, i2])

        if len(inliers) > len(largest_set):
            largest_set = inliers[:]
        #print(len(largest_set))
    ## END
    assert isinstance(largest_set, list)
    return largest_set


def FindBestMatches(descriptors1, descriptors2, threshold):
    """
    This function takes in descriptors of image 1 and image 2,
    and find matches between them. See assignment instructions for details.
    Inputs:
        descriptors: a K-by-128 array, where each row gives a descriptor
        for one of the K keypoints.  The descriptor is a 1D array of 128
        values with unit length.
        threshold: the threshold for the ratio test of "the distance to the nearest"
                   divided by "the distance to the second nearest neighbour".
                   pseudocode-wise: dist[best_idx]/dist[second_idx] <= threshold
    Outputs:
        matched_pairs: a list in the form [(i, j)] where i and j means
                       descriptors1[i] is matched with descriptors2[j].
    """
    assert isinstance(descriptors1, np.ndarray)
    assert isinstance(descriptors2, np.ndarray)
    assert isinstance(threshold, float)
    ## START

    matched_pairs = []
    
    # d1과 d2이 같은 feature인지 확인하는 방법은, angle을 기준으로 한다.
    # d1 pixel의 vector와 d2 pixel의 vector를 비교한다.
    # 계산된 값이 작을수록 유사하다고 할 수 있다.
    for idx1, d1 in enumerate(descriptors1):
        results = []
        for idx2, d2 in enumerate(descriptors2):
            # a (내적) b = |a| * |b| * cos(theta)
            # matrix_dot = a (내적) b
            matrix_dot = np.dot(d1, d2)
            
            # cos(theta) = matrix_dot / |a| / |b|
            cos_theta = matrix_dot / np.linalg.norm(d1) / np.linalg.norm(d2)

            # theta = cos^(-1)(cos_theta)
            theta = math.acos(cos_theta)

            # results에 계산한 값 잠시 저장해둔다.
            results.append([idx1, idx2, theta])

        # 위에서 계산한 값을 기준으로 sorting을 한다.
        results = sorted(results, key=lambda x: x[2])

        # dist[best_idx]/dist[second_idx] <= threshold
        if results[0][2] / results[1][2] <= threshold:
            # results에는 idx1, idx2, theta로 저장되어있다. 
            # theta를 제외하고 앞에 두 값만 matched_pairs list에 저장해야하므로, [0:2]로 slicing을 해준다.
            matched_pairs.append(results[0][0:2])

    ## END
    return matched_pairs


def FindBestMatchesRANSAC(
        keypoints1, keypoints2,
        descriptors1, descriptors2, threshold,
        orient_agreement, scale_agreement):
    """
    Note: you do not need to change this function.
    However, we recommend you to study this function carefully
    to understand how each component interacts with each other.

    This function find the best matches between two images using RANSAC.
    Inputs:
        keypoints1, 2: keypoints from image 1 and image 2
            stored in np.array with shape (num_pts, 4)
            each row: row, col, scale, orientation
        descriptors1, 2: a K-by-128 array, where each row gives a descriptor
        for one of the K keypoints.  The descriptor is a 1D array of 128
        values with unit length.
        threshold: the threshold for the ratio test of "the distance to the nearest"
                   divided by "the distance to the second nearest neighbour".
                   pseudocode-wise: dist[best_idx]/dist[second_idx] <= threshold
        orient_agreement: in degrees, say 30 degrees.
        scale_agreement: in floating points, say 0.5
    Outputs:
        matched_pairs_ransac: a list in the form [(i, j)] where i and j means
        descriptors1[i] is matched with descriptors2[j].
    Detailed instructions are on the assignment website
    """
    orient_agreement = float(orient_agreement)
    assert isinstance(keypoints1, np.ndarray)
    assert isinstance(keypoints2, np.ndarray)
    assert isinstance(descriptors1, np.ndarray)
    assert isinstance(descriptors2, np.ndarray)
    assert isinstance(threshold, float)
    assert isinstance(orient_agreement, float)
    assert isinstance(scale_agreement, float)
    matched_pairs = FindBestMatches(
        descriptors1, descriptors2, threshold)
    matched_pairs_ransac = RANSACFilter(
        matched_pairs, keypoints1, keypoints2,
        orient_agreement, scale_agreement)
    return matched_pairs_ransac
