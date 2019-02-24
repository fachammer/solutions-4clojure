(ns solutions-4clojure.075-eulers-totient-function)

(defn euler-totient [x]
  (let [greatest-common-divisor (fn greatest-common-divisor [a b]
                                  (cond
                                    (= 0 b) a
                                    (= 0 a) b
                                    (>= a b) (recur (- a b) b)
                                    :else (recur a (- b a))))]
    (->> (range x)
         (filter (fn [n] (= 1 (greatest-common-divisor n x))))
         count)))
