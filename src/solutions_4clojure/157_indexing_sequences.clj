(ns solutions-4clojure.157-indexing-sequences)

(defn index-seq [coll]
  (map-indexed (fn [i x] [x i]) coll))
