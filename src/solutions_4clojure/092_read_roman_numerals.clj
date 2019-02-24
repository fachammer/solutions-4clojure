(ns solutions-4clojure.092-read-roman-numerals)

(defn roman-numeral->int [numeral]
  (let [digit-value {\I 1
                     \V 5
                     \X 10
                     \L 50
                     \C 100
                     \D 500
                     \M 1000}]
    (first (reduce (fn [[value previous-digit] next-digit]
                     [(if (> next-digit previous-digit)
                        (+ value (- next-digit previous-digit previous-digit))
                        (+ value next-digit))
                      next-digit])
                   [0 Integer/MAX_VALUE]
                   (map digit-value numeral)))))
