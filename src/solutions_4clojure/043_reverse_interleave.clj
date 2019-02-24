(ns solutions-4clojure.043-reverse-interleave)

(defn reverse-interleave [coll n]
  (map (fn [k] (take-nth n (drop k coll))) (range 0 n)))
