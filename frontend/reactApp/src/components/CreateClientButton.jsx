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
import CreateClientForm from "./CreateClientForm.jsx";
import {AddIcon} from "@chakra-ui/icons";

const CloseIcon = () => "x";

const CreateClientButton = ({disable}) => {
    const { isOpen, onOpen, onClose } = useDisclosure()
    const handleRefresh = async () => {
        onClose();
        window.location.reload();
    };
    return <>
        <Button
            isDisabled={disable}
            _hover={{
                transform: 'translateY(-2px)',
                boxShadow: 'lg'
            }}
            onClick={onOpen}
        >
            {<AddIcon/>}

        </Button>
        <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
            <DrawerOverlay />
            <DrawerContent>
                <DrawerCloseButton />
                <DrawerHeader>Create new client</DrawerHeader>

                <DrawerBody>
                    <CreateClientForm
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

export default CreateClientButton;