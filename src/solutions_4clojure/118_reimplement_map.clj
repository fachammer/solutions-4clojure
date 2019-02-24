(ns solutions-4clojure.118-reimplement-map)

(defn my-map [f coll]
  (lazy-seq
   (when coll
     (cons (f (first coll)) (my-map f (next coll))))))
