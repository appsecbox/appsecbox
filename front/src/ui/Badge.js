import {Link} from "react-router-dom";
import {Button} from "@material-ui/core";

/**
 *
 * @param props should contain: type, id, name
 */

const Badge = (props) => {
    return (
        <Button variant="outlined">
            <Link to={"/"+props.type+"/"+props.id}>{props.name}</Link>
        </Button>
    )
}

export default Badge