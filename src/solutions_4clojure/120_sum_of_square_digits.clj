(ns solutions-4clojure.120-sum-of-square-digits)

(defn smaller-than-sum-of-digit-squares [coll]
  (let [digits (fn [x] (map #(Integer/valueOf (str %)) (str x)))]
    (filter #(< % (apply + (map (fn [x] (* x x)) (digits %)))) coll)))
