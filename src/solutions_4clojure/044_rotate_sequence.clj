(ns solutions-4clojure.044-rotate-sequence)

(defn rotate [n coll]
  (take (count coll) (drop (mod n (count coll)) (cycle coll))))
