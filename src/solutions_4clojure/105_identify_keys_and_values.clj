(ns solutions-4clojure.105-identify-keys-and-values)

(defn key-value-identification [coll]
  (->> coll
       (partition-by keyword?)
       (partition 2)
       (mapcat (fn [[ks vs]]
                 (apply list
                        [(last ks) vs]
                        (map (fn [k] [k []]) (drop-last ks)))))
       (into {})))
