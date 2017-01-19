package shareevent.repositories.inmem

import org.joda.time.DateTime
import shareevent.DomainContext
import shareevent.model.Location

import scala.util.{Success, Try}

class InMemoryContext extends DomainContext {


  class InMemoryRepo extends Repository {

    private var participants = Set[Participant]()

    def storeParticipant(participant: Participant): Try[Unit] = {
      participants = participants + participant
      Success(())
    }

    def retrieveParticipant(login: String): Try[Option[Participant]] =
      Try(participants.find(_.login == login))

    def deleteParticipant(login: String): Try[Boolean] = {
      for {op <- retrieveParticipant(login) } yield {
        op.foreach{ p =>
          participants = participants - p
        }
        op.isDefined
      }
    }

    lazy val locationDAO: DAO[Long,Location] = ???




  }

  def checkExistence[T](op:Option[T]): Try[T] =
    op.toRight(new IllegalStateException("login not found")).toTry

  override val repository = new InMemoryRepo {}

  override def currentTime: DateTime = new DateTime()

}