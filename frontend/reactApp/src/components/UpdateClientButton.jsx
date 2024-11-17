import {
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent,
    DrawerFooter,
    DrawerHeader,
    DrawerOverlay,
    useDisclosure
} from "@chakra-ui/react";
import UpdateClientForm from "./UpdateClientForm.jsx";
import {deleteInsuranceClient, getInsuranceClient, updateInsuranceClient} from "../services/insuranceClient.js";
import {errorNotification, successNotification} from "../services/notification.js";
import {EditIcon} from "@chakra-ui/icons";

const CloseIcon = () => "x";


const UpdateClientButton = ({client,disable}) => {
    const { isOpen, onOpen, onClose } = useDisclosure()
    const handleRefresh = async () => {
            onClose();
            window.location.reload();
    };
    return <>
        <Button
            onClick={onOpen}
            _hover={{
                transform: 'translateY(-2px)',
                boxShadow: 'lg'
            }}
            isDisabled={disable}
            variant={"unstyled"}
        >
            {<EditIcon/>}

        </Button>
        <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
            <DrawerOverlay />
            <DrawerContent>
                <DrawerCloseButton />
                <DrawerHeader>Update {client.email}</DrawerHeader>

                <DrawerBody>
                    <UpdateClientForm
                        {...client}
                    />
                </DrawerBody>

                <DrawerFooter>
                    <Button
                        leftIcon={<CloseIcon/>}
                        colorScheme={"teal"}
                        onClick={handleRefresh}>
                        Close
                    </Button>
                </DrawerFooter>
            </DrawerContent>
        </Drawer>
    </>

}

export default UpdateClientButton;