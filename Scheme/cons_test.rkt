(cons 1 2) ; (1 . 2)
(cons 3 (cons 1 2)) ; (3 1 . 2)
(cons 4 (cons 3 (cons 1 2))) ; (4 3 1 . 2)

(cons '3 '(1 2)) ; (3 1 2)

(list 3 1 2) ; (3 1 2)
(car '(3 1 2)) ; 3
(cdr '(3 1 2)) ; (1 2)
'() ; () empty list
(pair? (cons 1 2)) ; #t