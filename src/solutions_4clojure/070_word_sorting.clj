(ns solutions-4clojure.070-word-sorting)

(defn sort-words [sentence]
  (->> (clojure.string/split sentence #" ")
       (map (fn [word] (apply str (remove #{\? \! \.} word))))
       (sort-by clojure.string/lower-case)))
