(ns solutions-4clojure.171-intervals)

(defn intervals [ints]
  (if-not (seq ints)
    []
    (let [coll (sort ints)]
      (let [[last-run runs] (reduce (fn [[[prev-arg :as current-run] runs] arg]
                                      (if (and prev-arg
                                               (contains? #{0 1} (- arg prev-arg)))
                                        [(cons arg current-run) runs]
                                        [[arg] (conj runs current-run)]))
                                    [[(first coll)] []]
                                    (rest coll))]
        (->> (conj runs last-run)
             (map reverse)
             (map (fn [run] [(first run) (last run)])))))))
