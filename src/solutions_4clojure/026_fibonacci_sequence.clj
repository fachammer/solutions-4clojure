(ns solutions-4clojure.026-fibonacci-sequence)

(defn fibonacci-sequence [n]
  (->> [1 1]
       (iterate (fn [[a b]] [b (+ a b)]))
       (map first)
       (take n)))
