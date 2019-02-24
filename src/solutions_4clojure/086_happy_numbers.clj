(ns solutions-4clojure.086-happy-numbers)

(defn happy-number? [n]
  (let [digits (fn [n] (map #(Integer/valueOf (str %)) (str n)))
        step (fn [n] (apply + (map (fn [d] (* d d)) (digits n))))]
    (contains? (->> n
                    (iterate step)
                    (reductions conj #{})
                    (partition 2 1)
                    (filter (partial apply =))
                    first
                    first)
               1)))
