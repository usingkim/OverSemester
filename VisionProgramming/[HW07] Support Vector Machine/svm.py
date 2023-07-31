import numpy as np
import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.svm import SVC

colors = ['red', 'blue', 'green']

## Part 1 Linear SVM Classifiers

# 1. create Datasets
r = [20, 30, 40]
m = [0.1, 1 ,10]
plt.figure(figsize=(10,10))
for i in range(3):
    for j in range(3):
        plt.subplot(3, 3, i * 3 + j + 1)
        p, c = datasets.make_blobs(random_state=r[j], cluster_std=1.2, centers=2)
        plt.scatter(p[:,0], p[:,1], c=[colors[c] for c in c])
        plt.xlabel("random state = " + str(r[j]))
        l = SVC(kernel='linear')
        l.fit(p, c)

plt.show()