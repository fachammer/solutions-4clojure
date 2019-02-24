(ns solutions-4clojure.099-product-digits)

(defn product-digits [a b]
  (let [product (* a b)
        number-of-digits (int (Math/floor (+ 1 (Math/log10 product))))]
    (first (reduce (fn [[digits remainder] d]
                     (let [digit (quot remainder (int (Math/pow 10 d)))]
                       [(conj digits digit)
                        (- remainder (* digit (int (Math/pow 10 d))))]))
                   [[] product]
                   (range (dec number-of-digits) -1 -1)))))
