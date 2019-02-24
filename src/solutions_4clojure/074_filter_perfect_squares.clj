(ns solutions-4clojure.074-filter-perfect-squares)

(defn perfect-squares [s]
  (->> (clojure.string/split s #",")
       (map #(Integer/valueOf %))
       (filter #(= % (-> % Math/sqrt Math/floor (Math/pow 2) int)))
       (interpose ",")
       (apply str)))
