(ns solutions-4clojure.063-group-a-sequence)

(defn groups [f coll]
  (reduce (fn [acc next-value]
            (let [k (f next-value)]
              (assoc acc k
                     (conj (or (acc k) []) next-value))))
          {} coll))
