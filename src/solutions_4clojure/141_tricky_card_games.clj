(ns solutions-4clojure.141-tricky-card-games)

(defn trick-winner-function [trump]
  (fn [[{lead-suit :suit} :as trick]]
    (apply max-key (fn [{:keys [suit rank]}]
                     (+ rank (* 13 (cond
                                     (= suit trump) 2
                                     (= suit lead-suit) 1
                                     :else 0))))
           trick)))
