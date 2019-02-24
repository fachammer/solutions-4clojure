(ns solutions-4clojure.067-prime-numbers)

(defn primes
  ([]
   (->> ['() (drop 2 (range))]
        (iterate (fn [[selected remaining]]
                   [(cons (first remaining) selected)
                    (remove #(= 0 (mod % (first remaining))) (rest remaining))]))
        rest
        (map first)
        (map first)))
  ([n] (take n (primes))))
