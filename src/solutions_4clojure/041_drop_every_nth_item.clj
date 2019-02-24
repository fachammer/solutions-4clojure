(ns solutions-4clojure.041-drop-every-nth-item)

(defn drop-every-nth [coll n]
  (apply concat (map drop-last (partition n n [0] coll))))
