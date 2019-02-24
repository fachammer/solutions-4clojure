(ns solutions-4clojure.112-sequs-horribilis)

(defn sequs-horribilis [max coll]
  (let [value (fn value [x] (if (coll? x) (apply + (map value x)) x))]
    (->> (reductions (fn [s next-el]
                       (if (coll? next-el)
                         (concat s [(sequs-horribilis (- max (value s)) next-el)])
                         (if (< max (+ (value next-el) (value s)))
                           s
                           (concat s [next-el]))))
                     [] coll)
         (partition-all 2 1)
         (drop-while (partial apply not=))
         first
         first)))
