(ns solutions-4clojure.131-sum-some-set-subsets)

(defn all-have-equivalent-subset-summation? [& sets]
  (let [power-set (fn power-set [s]
                    (reduce (fn [acc el]
                              (apply conj acc (map #(conj % el) acc))) #{#{}} s))
        subset-sums (fn [s]
                      (set (map (partial apply +) (remove #{#{}} (power-set s)))))
        intersection (fn intersection [s & sets]
                       (if-not sets s (set (filter s (apply intersection sets)))))]
    (not= #{} (apply intersection (map subset-sums sets)))))
