(ns solutions-4clojure.049-split-a-sequence)

(defn split [i coll]
  [(take i coll) (drop i coll)])
