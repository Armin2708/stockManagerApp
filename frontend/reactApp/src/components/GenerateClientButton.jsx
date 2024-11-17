import {Button} from "@chakra-ui/react";
import {useGenerateClients} from "../services/useGenerateClients.js";
import {RepeatIcon} from "@chakra-ui/icons";

export default function GenerateClientButton(){

    const { generateClients, isLoading, error } = useGenerateClients();
    return(
        <Button
            _hover={{
                transform: 'translateY(-2px)',
                boxShadow: 'lg'
            }}
            onClick={()=>generateClients()}
        >
            {<RepeatIcon/>}
        </Button>
    )
}