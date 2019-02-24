(ns solutions-4clojure.100-least-common-multiple)

(defn least-common-multiple [a & args]
  (reduce (fn [acc next-value]
            (->> acc
                 (iterate (partial + acc))
                 (filter #(= 0 (mod % next-value)))
                 first))
          a args))
