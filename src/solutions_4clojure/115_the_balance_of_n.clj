(ns solutions-4clojure.115-the-balance-of-n)

(defn balanced? [n]
  (let [digits (vec (map #(Integer/valueOf (str %)) (str n)))]
    (= (apply + (subvec digits 0 (quot (count digits) 2)))
       (apply + (subvec digits (quot (inc (count digits)) 2))))))
