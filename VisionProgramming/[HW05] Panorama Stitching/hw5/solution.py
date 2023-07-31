from unittest import result
import numpy as np
import cv2
import math
import random

def FindBestMatches(descriptors1, descriptors2, threshold):
    assert isinstance(descriptors1, np.ndarray)
    assert isinstance(descriptors2, np.ndarray)
    assert isinstance(threshold, float)

    matched_pairs = []
    
    for idx1, d1 in enumerate(descriptors1):
        results = []
        for idx2, d2 in enumerate(descriptors2):
            matrix_dot = np.dot(d1, d2)
            cos_theta = matrix_dot / np.linalg.norm(d1) / np.linalg.norm(d2)
            theta = math.acos(cos_theta)
            results.append([idx1, idx2, theta])

        results = sorted(results, key=lambda x: x[2])

        if results[0][2] / results[1][2] <= threshold:
            matched_pairs.append(results[0][0:2])
    return matched_pairs


def KeypointProjection(xy_points, h):
    """
    This function projects a list of points in the source image to the
    reference image using a homography matrix `h`.
    Inputs:
        xy_points: numpy array, (num_points, 2)
        h: numpy array, (3, 3), the homography matrix
    Output:
        xy_points_out: numpy array, (num_points, 2), input points in
        the reference frame.
    """
    assert isinstance(xy_points, np.ndarray)
    assert isinstance(h, np.ndarray)
    assert xy_points.shape[1] == 2
    assert h.shape == (3, 3)

    # START
    # 2d point -> homogeneous coordinate
    np_xy_points = [np.append(xy, [1]) for xy in xy_points]
    np_h = np.array(h)

    # perform the projection by a matrix multiplication
    np_result = [np.dot(np_h, xy) for xy in np_xy_points]

    # convert back the projected points in homogeneous coordinate 
    # to the regular coordinate by dividing through the extra dimension.
    xy_points_out = []
    for r in np_result:
        # 0으로 나눠줄 수 없으므로 그에 준하는 1 ^ (-10)으로 변환시켜준다.
        if r[2] == 0:
            r[2] = 1 ** (-10)
        xy_points_out.append([r[0] / r[2], r[1] / r[2]])
    xy_points_out = np.array(xy_points_out)

    # END
    return xy_points_out

def RANSACHomography(xy_src, xy_ref, num_iter, tol):

    assert isinstance(xy_src, np.ndarray)
    assert isinstance(xy_ref, np.ndarray)
    assert xy_src.shape == xy_ref.shape
    assert xy_src.shape[1] == 2
    assert isinstance(num_iter, int)
    assert isinstance(tol, (int, float))
    tol = tol * 1.0

    # START
    # xy coordinates of matches between a source and ref
    bests = FindBestMatches(xy_src, xy_ref, 0.6)
    h = []
    # inlier의 수가 가장 많을때를 저장하는 변수
    best_inlier = -1

    for _ in range(num_iter):
        # 랜덤으로 bests에서 4개를 고른다.
        samples = random.sample(bests, 4)
        num_of_inlier = 0

        src = [[xy_src[samples[i][0]][0], xy_src[samples[i][0]][1]] for i in range(4)]
        ref = [[xy_ref[samples[i][1]][0], xy_ref[samples[i][1]][1]] for i in range(4)]

        # use RANSAC to find the biggest consensus set 
        # of matches to compute the final homography matrix.
        h_candi, _ = cv2.findHomography(xy_src, xy_ref)
        for i in range(4):
            pro = np.dot(np.array(h_candi), np.array(src[i] + [1]))
            p_ref = [pro[0] / pro[2], pro[1] / pro[2]]

            # Euclidean distance
            dist = (ref[i][0] - p_ref[0]) ** 2 + (ref[i][1] - p_ref[1]) ** 2
            dist = math.sqrt(dist)

            if dist < tol:
                num_of_inlier += 1

        # inlier의 수가 가장 많은 것을 찾기 위함
        if num_of_inlier > best_inlier:
            h = h_candi
            best_inlier = num_of_inlier
    
    # END
    assert isinstance(h, np.ndarray)
    assert h.shape == (3, 3)
    return h
