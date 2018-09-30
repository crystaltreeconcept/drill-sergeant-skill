import scala.collection.immutable.Stream.cons
//TODO: remove this 777



val in1 = "bdefgabcdg"
in1.foldLeft((Set[Char](), List[Char]()))((prev, nother) => {
  if (!prev._1.contains(nother))
    (prev._1 + nother, nother :: prev._2)
  else
    prev
})
._2.reverse
