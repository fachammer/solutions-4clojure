(ns solutions-4clojure.060-sequence-reductions)

(defn my-reductions
  ([f coll]
   (my-reductions f (f (first coll)) (rest coll)))
  ([f val coll]
   (map (fn [el] (reduce f val (take el coll)))
        (concat [0] (map-indexed (fn [i _] (inc i)) coll)))))
