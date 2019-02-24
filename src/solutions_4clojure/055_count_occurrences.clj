(ns solutions-4clojure.055-count-occurrences)

(defn my-frequencies [coll]
  (into {} (map (fn [[k v]] [k (count v)]) (group-by identity coll))))
