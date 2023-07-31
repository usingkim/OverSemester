; 1. (1+39) * (53-45)
; eval and apply

; eval
; (+ 1 39)
; (- 53 45)

; apply *

(* (+ 1 39) (- 53 45))
; result = 320

; 2. (1020/39) + (45 * 2)
; (/ 1020 39)
; (* 45 2)

(+ (/ 1020 39) (* 45 2))
; result 116 and (2 / 13)

; 3. add 39, 48, 72, 23 ,91
(+ 29 48 72 23 91)
; 263
; remember !! this is list

; 4. average 39 48 72 23 91
(/ (+ 39 48 72 23 91) 5)
; 54 and 3/5
; to floating point
(exact->inexact (/ (+ 39 48 72 23 91) 5))
; 54.6
; exact->inexact : this is function's name

; 5. circle ratio, pi using atan
(* 4 (atan 1.0))
; 6. exp(2/3)
(exp 2/3)
; 7. power
(expt 3 4)
; 8. logarithm of 1000
(log 1000)