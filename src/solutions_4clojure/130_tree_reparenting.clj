(ns solutions-4clojure.130-tree-reparenting)

(defn reparent-tree [new-root tree]
  (let [path-to-new-root (fn path-to-new-root [[root & children]]
                           (cond
                             (= root new-root)  (list root)
                             children           (cons root
                                                      (->> children
                                                           (map path-to-new-root)
                                                           (remove nil?)
                                                           first))))
        reparent (fn [new-root [root & children]]
                   (let [{new-children false
                          [[_ & new-root-node-children]] true} (group-by (comp boolean #{new-root} first)
                                                                         children)]
                     (apply list new-root
                            (concat new-root-node-children
                                    (list (apply list root new-children))))))]
    (->> [(rest (path-to-new-root tree)) tree]
         (iterate (fn [[[next-node & remaining] tree]]
                    (when next-node
                      [remaining (reparent next-node tree)])))
         (take-while (complement nil?))
         last
         second)))
