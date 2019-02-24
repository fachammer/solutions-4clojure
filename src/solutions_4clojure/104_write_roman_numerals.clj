(ns solutions-4clojure.104-write-roman-numerals)

(defn int->roman-numeral [n]
  (let [numeral-map {"I" ["V" "X"] "X" ["L" "C"] "C" ["D" "M"] "M" []}
        digits (map #(Integer/valueOf (str %)) (str n))
        subtractify (fn [numeral d]
                      (let [[five ten] (numeral-map numeral)]
                        (apply str (case d
                                     (0 1 2 3) (repeat d numeral)
                                     4 (list numeral five)
                                     (5 6 7 8) (apply list five
                                                      (repeat (- d 5) numeral))
                                     9 (list numeral ten)))))]
    (->> digits
         (concat (repeat (- 4 (count digits)) 0))
         (map subtractify ["M" "C" "X" "I"])
         (apply str))))
