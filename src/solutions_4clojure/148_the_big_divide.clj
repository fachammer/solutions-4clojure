(ns solutions-4clojure.148-the-big-divide)

(defn sum-of-evenly-divisible-numbers-smaller-than [n a b]
  (let [[asum bsum absum] (map #(* % 1/2 (quot (dec n) %) (inc (quot (dec n) %)))
                               [a b (* a b)])]
    (+' asum bsum (- absum))))
