(ns solutions-4clojure.114-global-take-while)

(defn take-nth-while [n pred coll]
  (->> coll
       (reductions
        (fn [[c sum] next-el]
          [(conj c next-el) (+ sum (if (pred next-el) 1 0))])
        [[] 0])
       (take-while #(> n (second %)))
       last
       first))
