; fact function

; if n < 0 -> X
;    n == 1 -> 1
; else n * fact(n-1)

; cond -> conditional ...
; #t : true
; #f : false

(define (fact n)
  (cond ((< n 0) #f)
        ((<= n 1) 1)
        (else (* n (fact(- n 1))))))
(fact 55)

; version 2
(define (fact2 n)
  (if (= n 0) 1(* n (fact2(- n 1)))))
(fact2 55)
