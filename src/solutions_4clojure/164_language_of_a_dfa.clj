(ns solutions-4clojure.164-language-of-a-dfa)

(defn dfa-language-strings [{:keys [states alphabet start accepts transitions word queue]
                             :as dfa}]
  (if-not queue
    (recur (assoc dfa
                  :queue (conj (clojure.lang.PersistentQueue/EMPTY) {:word ""
                                                                     :current-state start})))
    (when-let [{:keys [word current-state]} (peek queue)]
      (let [next-queue (map (fn [[transition state]] {:word (str word transition)
                                                      :current-state state})
                            (transitions current-state))]
        (lazy-seq (concat (map :word (filter (comp accepts :current-state) next-queue))
                          (dfa-language-strings (assoc dfa :queue (if-not (seq next-queue)
                                                                    (pop queue)
                                                                    (apply conj (pop queue) next-queue))))))))))
