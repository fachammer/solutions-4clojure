(ns solutions-4clojure.069-my-merge-with)

(defn my-merge-with [f & maps]
  (reduce (fn [acc m] (reduce (fn [result [k v]]
                                (assoc result k (if (acc k) (f (acc k) v) v)))
                              acc m)) {} maps))
