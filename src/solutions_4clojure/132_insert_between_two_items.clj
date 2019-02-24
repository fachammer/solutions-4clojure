(ns solutions-4clojure.132-insert-between-two-items)

(defn insert-between [pred v coll]
  (if (first coll)
    (->> [coll (rest coll)]
         (apply mapcat (fn [a b] (if (pred a b) [v b] [b])))
         (cons (first coll)))
    coll))
