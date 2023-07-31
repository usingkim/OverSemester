import numpy as np
import matplotlib.pyplot as plt

# img1 = plt.imread('./data/warrior_a.jpg')
# img2 = plt.imread('./data/warrior_b.jpg')

# cor1 = np.load("./data/warrior_a.npy")
# cor2 = np.load("./data/warrior_b.npy")

img1 = plt.imread('./data/graffiti_a.jpg')
img2 = plt.imread('./data/graffiti_b.jpg')

cor1 = np.load("./data/graffiti_a.npy")
cor2 = np.load("./data/graffiti_b.npy")

def compute_fundamental(x1,x2):
    n = x1.shape[1]
    if x2.shape[1] != n:
        exit(1)

    F = None
    ### YOUR CODE BEGINS HERE
    
    # build matrix for equations in Page 51
    # x1과 x2는 homogeneous 이지만, normalize 되어있으므로 [0]과 [1]을 사용해도 무방하다.
    u = x1[0][:]
    v = x1[1][:]

    u_ = x2[0][:]
    v_ = x2[1][:]

    # ppt 51 페이지에 있는 식을 이용해서 행렬을 생성한다.
    uv_matrix = np.array([[u[idx] * u_[idx], v[idx] * u_[idx], u_[idx], 
                    u[idx] * v_[idx], v[idx] * v_[idx], v_[idx],
                    u[idx], v[idx], 1] for idx in range(n)])

    # compute linear least square solution
    # singular value decomposition을 이용해 V를 도출한다.
    # AA^T 이용해야하므로, V가 의미있는 value
    _, _,V = np.linalg.svd(uv_matrix)
    # V를 거꾸로해서 reshape 하면 F 가 된다.
    F = V[-1].reshape(3,3)
        
    # constrain F : make rank 2 by zeroing out last singular value
    U, S, V = np.linalg.svd(F)
    # 차수가 2여야하므로 마지막 value는 0으로 만들어준다.
    S[2] = 0
    # F' = US'V
    # 이 때 S = (3,)이므로 np.diag 함수를 이용해준다.
    F = U @ np.diag(S) @ V
    F = F / F[2][2]
    ### YOUR CODE ENDS HERE
    
    return F


def compute_norm_fundamental(x1,x2):
    n = x1.shape[1]
    if x2.shape[1] != n:
        exit(1)

    # normalize image coordinates
    x1 = x1 / x1[2]
    mean_1 = np.mean(x1[:2],axis=1)
    S1 = np.sqrt(2) / np.std(x1[:2])
    T1 = np.array([[S1,0,-S1*mean_1[0]],[0,S1,-S1*mean_1[1]],[0,0,1]])
    x1 = T1 @ x1
    
    x2 = x2 / x2[2]
    mean_2 = np.mean(x2[:2],axis=1)
    S2 = np.sqrt(2) / np.std(x2[:2])
    T2 = np.array([[S2,0,-S2*mean_2[0]],[0,S2,-S2*mean_2[1]],[0,0,1]])
    x2 = T2 @ x2

    # compute F with the normalized coordinates
    F = compute_fundamental(x1,x2)

    # reverse normalization
    F = T2.T @ F @ T1
    
    return F


def compute_epipoles(F):
    e1 = None
    e2 = None
    ### YOUR CODE BEGINS HERE
    # V를 이용해 epipole들을 추출한다.
    # homogeneous 이므로, e1 / e1[2] 를 통해 normalize 해줘야한다.
    _, _, V = np.linalg.svd(F)
    e1 = V[-1]
    e1 = e1 / e1[2]

    _, _, V = np.linalg.svd(np.transpose(F))
    e2 = V[-1]
    e2 = e2 / e2[2]

    ### YOUR CODE ENDS HERE
    
    return e1, e2


def draw_epipolar_lines(img1, img2, cor1, cor2):
    F = compute_norm_fundamental(cor1, cor2)

    e1, e2 = compute_epipoles(F)
    ### YOUR CODE BEGINS HERE

    # 다양한 컬러를 위한 배열
    colors = ['red', 'blue', 'green', 'white', 'black', 'purple', 'yellow', 
        'orange', 'green', 'pink', 'grey']

    plt.figure(figsize=(10,10))

    # 끊기지 않는 직선을 위해서 axline을 사용하고, 해당 점을 표시하기 위해서 scatter를 사용한다.
    # axline은 cor 점과 epipole을 이용한 직선 방정식을 만들어서 활용한다.
    plt.subplot(221)
    for i in range(cor1[0].shape[0]):
        plt.axline([cor1[0][i], cor1[1][i]], [e1[1] / e1[2] * -1, e1[1]], c=colors[i % len(colors)])
        plt.scatter(cor1[0][i], cor1[1][i], c=colors[i % len(colors)])
    plt.imshow(img1)

    plt.subplot(222)
    for i in range(cor2[0].shape[0]):
        plt.axline([cor2[0][i], cor2[1][i]], [e2[1] / e2[2] * -1, e2[1]], c=colors[i % len(colors)])
        plt.scatter(cor2[0][i], cor2[1][i], c=colors[i % len(colors)])
    plt.imshow(img2)
    plt.show()
    ### YOUR CODE ENDS HERE

    return

draw_epipolar_lines(img1, img2, cor1, cor2)