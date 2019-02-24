(ns solutions-4clojure.078-reimplement-trampoline)

(defn my-trampoline [f & args]
  (->> (apply f args)
       (iterate (fn [v] (if (fn? v) (v) v)))
       (drop-while fn?)
       first))
