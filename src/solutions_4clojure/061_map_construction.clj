(ns solutions-4clojure.061-map-construction)

(defn my-zipmap [key-vector value-vector]
  (into {} (map (fn [k v] [k v]) key-vector value-vector)))
