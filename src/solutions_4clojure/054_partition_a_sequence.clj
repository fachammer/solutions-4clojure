(ns solutions-4clojure.054-partition-a-sequence)

(defn my-partition [n coll]
  (map (fn [i] (take n (drop (* i n) coll))) (range (quot (count coll) n))))
