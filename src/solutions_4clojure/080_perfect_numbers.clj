(ns solutions-4clojure.080-perfect-numbers)

(defn perfect-number? [n]
  (let [divisors (filter #(= 0 (mod n %)) (range 1 n))]
    (= n (apply + divisors))))
