(ns solutions-4clojure.066-greatest-common-divisor)

(defn greatest-common-divisor [a b]
  (cond
    (= 0 b) a
    (= 0 a) b
    (>= a b) (recur (- a b) b)
    :else (recur a (- b a))))
