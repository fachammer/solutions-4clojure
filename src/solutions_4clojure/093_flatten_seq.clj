(ns solutions-4clojure.093-flatten-seq)

(defn flatten-seq [coll]
  (mapcat (fn unwrap [el]
            (if-not (every? sequential? el)
              [el]
              (mapcat unwrap el)))
          coll))
