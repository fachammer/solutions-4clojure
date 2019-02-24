(ns solutions-4clojure.116-prime-sandwich)

(defn balanced-prime? [n]
  (let [primes (map (comp first first)
                    (rest (iterate (fn [[selected remaining]]
                                     [(cons (first remaining) selected)
                                      (remove #(= 0 (mod % (first remaining)))
                                              (rest remaining))])
                                   ['() (drop 2 (range))])))
        [smaller-primes [p & larger-primes]] (split-with (partial > n) primes)]
    (and (not (> 1 (count smaller-primes)))
         (= n p)
         (= (* 2 n) (+ (last smaller-primes) (first larger-primes))))))
