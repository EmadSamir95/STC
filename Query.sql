select A."user_id", "username", "training_id", "training_date", count(*)
from "User" A, "Training_details" B
where A."user_id" = B."user_id"
group by A."user_id", "username", "training_id", "training_date"
having count(*) > 1 order by "training_date" desc;